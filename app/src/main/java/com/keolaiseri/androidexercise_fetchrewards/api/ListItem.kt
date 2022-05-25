package com.keolaiseri.androidexercise_fetchrewards.api

import androidx.lifecycle.LiveData
import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass

/**
 * Gets List Item JSON information from the List API Retrofit service and updates the
 * [ListItem] and [ListApiService]. The keys and value types must match the Json response.
 */

@JsonInclude(content = JsonInclude.Include.NON_EMPTY)
data class ListItem(
    var id: Int?,
    var listId: Int?,
    var name: String?
)
