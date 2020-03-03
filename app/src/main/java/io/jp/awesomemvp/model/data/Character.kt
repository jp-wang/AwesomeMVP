package io.jp.awesomemvp.model.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "characters")
data class Character @JvmOverloads constructor(
    var name: String = "",
    var height: String = "",
    var mass: String = "",
    @ColumnInfo(name = "birth_year") @SerializedName("birth_year") var birthYear: String = "",
    var gender: String = "",
    @PrimaryKey @ColumnInfo(name = "url") @SerializedName("url") var id: String = UUID.randomUUID().toString(),
    var avatar: String = ""
) : Parcelable

data class Avatar(val name: String, val image: String)

data class CharacterResult(val results: List<Character>)