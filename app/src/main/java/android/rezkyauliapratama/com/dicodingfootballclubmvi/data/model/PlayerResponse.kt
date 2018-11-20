package android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName(value="player", alternate= arrayOf("players"))
    val player : List<Player>
)