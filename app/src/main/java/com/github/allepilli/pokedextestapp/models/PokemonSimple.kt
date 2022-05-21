package com.github.allepilli.pokedextestapp.models

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.reflect.Type



//object PokemonController {
//    private val gsonConverterFactory = GsonBuilder()
//        .registerTypeAdapter(object: TypeToken<List<PokemonSimple>>() {}.type, PokemonSimpleDeserializer())
//        .create()
//        .let { gson -> GsonConverterFactory.create(gson) }
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://stoplight.io/mocks/appwise-be/pokemon/57519009/")
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(gsonConverterFactory)
//        .build()
//
//    private val pokemonService = retrofit.create(PokemonService::class.java)
//
//    fun getPokemon(): List<PokemonSimple> {
//        val response = pokemonService.getPokemon()
//
//        if (response.isSuccessful) {
//            return response.body()!!
//        } else {
//            error(response.errorBody()!!.string())
//        }
//    }
//}
//
//private interface PokemonService {
//    @GET("pokemon")
//    fun getPokemon(): Response<List<PokemonSimple>>
//}

object PokemonController {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://stoplight.io/mocks/appwise-be/pokemon/57519009/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val pokemonService = retrofit.create(PokemonService::class.java)

    fun getPokemon(callback: Callback<PokemonSimpleListWrapper>) {
        pokemonService.getPokemon().enqueue(callback)
    }
}

private interface PokemonService {
    @GET("pokemon")
    fun getPokemon(): Call<PokemonSimpleListWrapper>
}

data class PokemonSimpleListWrapper(
    val list: List<PokemonSimple>
)

data class PokemonSimple(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprites: Map<String, String>,

    @SerializedName("types")
    val types: List<PokemonType>
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: IndividualType,
)

data class IndividualType(@SerializedName("name") val name: String)

private class PokemonSimpleDeserializer: JsonDeserializer<PokemonSimpleListWrapper> {
    @ExperimentalStdlibApi
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonSimpleListWrapper {
        val pokemonObjectArray = json?.asJsonArray ?: error("Did not receive JSON object")
        Log.i("Deserializer", "called with $json")

        return PokemonSimpleListWrapper(
            buildList {
                pokemonObjectArray.forEach { pokemonObject ->
                    add(pokemonObject.asJsonObject.deserializePokemon())
                }
            }
        )
    }

    private fun JsonObject.deserializePokemon(): PokemonSimple = PokemonSimple(
        id = get("id").asInt,
        name = get("name").asString,
        sprites = getAsJsonObject("sprites")
            .entrySet()
            .associate { (key, value) -> key!! to value!!.asString },
        types = getAsJsonArray("types")
            .map { it.asJsonObject }
            .map { typeObject ->
                PokemonType(
                    slot = typeObject.get("slot").asInt,
                    type = typeObject.get("type")
                        .asJsonObject
                        .get("name")
                        .asString
                        .let(::IndividualType)
                )
            }
    )
}
