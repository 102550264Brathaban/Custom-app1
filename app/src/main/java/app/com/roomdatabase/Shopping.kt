package app.com.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Shopping {

    @PrimaryKey
    var id : Int? = null

    var name : String = ""

    var amount : Int = 0

    var IsBigSale : Boolean = false

}