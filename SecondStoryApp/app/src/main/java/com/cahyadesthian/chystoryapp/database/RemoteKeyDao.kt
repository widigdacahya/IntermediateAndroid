package com.cahyadesthian.chystoryapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: List<RemoteKeyEntity>)

    @Query("SELECT * FROM remote_key WHERE id =:id")
    suspend fun getRemoteKeyId(id:String): RemoteKeyEntity?

    @Query("DELETE FROM remote_key")
    suspend fun deleteAllKey()


}