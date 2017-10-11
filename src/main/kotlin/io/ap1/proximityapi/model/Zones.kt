package io.ap1.proximityapi.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Zones(val beacons: MutableList<Beacon>?,
                 val geofences: MutableList<Geofence>?,
                 val networks: MutableList<Network>?)