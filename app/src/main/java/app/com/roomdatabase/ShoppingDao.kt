package app.com.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingDao {

    @Insert
    suspend fun SaveShopping(shopping : Shopping)

    @Delete
    suspend fun DeleteShopping (shopping : Shopping)

    @Query("Select * from Shopping ")
    fun ShowShopping() : LiveData<List<Shopping>>

    @Query("delete from Shopping")
    suspend fun DeleteAll()

}