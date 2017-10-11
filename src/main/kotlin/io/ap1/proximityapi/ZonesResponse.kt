package io.ap1.proximityapi

import com.fasterxml.jackson.annotation.JsonInclude
import io.ap1.proximityapi.model.Zones

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ZonesResponse(val zones: Zones)