package io.jp.awesomemvp.model.repo.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.jp.awesomemvp.model.data.Character
import io.jp.awesomemvp.model.repo.source.local.CharactersDao

/**
 * The Room Database that contains the Character table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [Character::class], version = 2, exportSchema = false)
abstract class StarwarDatabase : RoomDatabase() {

    abstract fun characterDao(): CharactersDao
}
