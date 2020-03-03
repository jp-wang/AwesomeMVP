package io.jp.awesomemvp.model.repo.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import io.jp.awesomemvp.model.data.Character
import io.jp.awesomemvp.model.data.Result
import io.jp.awesomemvp.model.repo.StarwarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarwarLocalDataSource(private val dao: CharactersDao) : StarwarRepository {

    override fun observeCharacters(): LiveData<Result<List<Character>>> {
        return dao.observe().map { Result.Success(it) }
    }

    override suspend fun refreshCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCharacter(id: String): Result<Character> = withContext(Dispatchers.IO) {
        try {
            val character = dao.getCharacterById(id)
            if (character != null) {
                return@withContext Result.Success(character)
            } else {
                return@withContext Result.Error(Exception("Character not found!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    suspend fun saveCharacter(character: Character) {
        dao.save(character)
    }

    suspend fun deleteAllCharaters() {
        dao.deleteAll()
    }


}