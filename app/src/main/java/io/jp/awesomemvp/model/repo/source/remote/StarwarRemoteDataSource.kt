package io.jp.awesomemvp.model.repo.source.remote

import androidx.lifecycle.LiveData
import io.jp.awesomemvp.model.repo.StarwarRepository
import io.jp.awesomemvp.model.data.Result
import io.jp.awesomemvp.model.data.Character

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


object StarwarRemoteDataSource : StarwarRepository {


    private val starwarService = StarwarService.create()

    private val avatarService = StarwarAvatarService.create()

    override fun observeCharacters(): LiveData<Result<List<Character>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun refreshCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    suspend fun getCharacters(): Result<List<Character>> = withContext(
        Dispatchers.IO
    ) {
        return@withContext try {
            val charactersDeferred = async { starwarService.getAllCharacters().results }
            val avatarsDeferred = async { avatarService.getCharacterAvatars() }

            val characters = charactersDeferred.await()
            val avatars = avatarsDeferred.await()
            characters.forEach { character ->
                character.avatar =
                    avatars.find { it.name.equals(character.name, true) }?.image.toString()
            }
            Result.Success(characters)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCharacter(id: String): Result<Character> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}