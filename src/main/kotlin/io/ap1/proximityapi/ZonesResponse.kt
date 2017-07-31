package io.ap1.proximityapi

import io.ap1.proximityapi.entities.Beacon
import io.ap1.proximityapi.entities.Geofence

class ZonesResponse(val beacons: MutableList<Beacon>, val geofences: MutableList<Geofence>)