package com.wellcherve.android.aadpracticeproject.models

import android.bluetooth.BluetoothAdapter
import android.os.Parcel
import android.os.Parcelable

 open class Leader(val name: String?, val country: String?, val badgeURl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeString(badgeURl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Leader> {
        override fun createFromParcel(parcel: Parcel): Leader {
            return Leader(parcel)
        }

        override fun newArray(size: Int): Array<Leader?> {
            return arrayOfNulls(size)
        }
    }
}

 class SkillIQLeader(name: String?, country: String?, badgeURl: String?, var score: Int?,
) : Leader(name, country, badgeURl) {

     constructor(parcel: Parcel) : this(
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readInt()

     ) {}

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         super.writeToParcel(parcel, flags)
         score?.let { parcel.writeInt(it) }
     }

     override fun toString(): String {
         return "$score skill IQ Score, $country"
     }
 }

 class LearningLeader(
  name: String?,
  country: String?,
  badgeURl: String?,
  var hours: Int?
) : Leader(name, country, badgeURl) {

     constructor(parcel: Parcel) : this(
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readInt()

     ) {}
     override fun writeToParcel(parcel: Parcel, flags: Int) {
         super.writeToParcel(parcel, flags)
         hours?.let { parcel.writeInt(it) }
     }

     override fun toString(): String {
         return "$hours learning hours, $country"
     }
 }


