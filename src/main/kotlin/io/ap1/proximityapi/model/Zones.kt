package io.ap1.proximityapi.model

data class Zones(val beacons: MutableList<Beacon>,
                 val geofences: MutableList<Geofence>,
                 val networks: MutableList<Network>)