package android.rezkyauliapratama.com.dicodingfootballclubmvi.data.network

import android.rezkyauliapratama.com.dicodingfootballclubmvi.BuildConfig


/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */
object EndPoints{
    private const val base_path = "api/v1/json/${BuildConfig.TSDB_API_KEY}"

    const val eventPastLeague = "$base_path/eventspastleague.php"
    const val eventNextLeague = "$base_path/eventsnextleague.php"
    const val searchTeam = "$base_path/search_all_teams.php"
    const val lookupTeam = "$base_path/lookupteam.php"
    const val lookupEvent = "$base_path/lookupevent.php"
    const val allPlayerInTeam = "$base_path/lookup_all_players.php"
    const val lookupPlayer= "$base_path/lookupplayer.php"
    const val searchTeamByName= "$base_path/searchteams.php"
    const val searchEventByName= "$base_path/searchevents.php"
}