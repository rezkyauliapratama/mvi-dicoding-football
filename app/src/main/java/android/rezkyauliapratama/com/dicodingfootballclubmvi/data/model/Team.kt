package android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rezky Aulia Pratama on 5/8/18.
 */
data class Team(
        @SerializedName("idTeam")
        val teamId: String,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,

        @SerializedName("strStadium")
        var teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null
)