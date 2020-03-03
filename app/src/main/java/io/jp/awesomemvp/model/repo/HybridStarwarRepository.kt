package io.jp.awesomemvp.model.repo

import androidx.lifecycle.LiveData
import io.jp.awesomemvp.model.data.Character
import io.jp.awesomemvp.model.data.Result
import io.jp.awesomemvp.model.repo.source.local.StarwarLocalDataSource
import io.jp.awesomemvp.model.repo.source.remote.StarwarRemoteDataSource

class HybridStarwarRepository(
    private val remoteDataSource: StarwarRemoteDataSource
    , private val localDataSource: StarwarLocalDataSource
) : StarwarRepository {

    override suspend fun getCharacter(id: String): Result<Character> {
        return localDataSource.getCharacter(id)
    }

    override fun observeCharacters(): LiveData<Result<List<Character>>> {
        return localDataSource.observeCharacters()
    }

    override suspend fun refreshCharacters() {
        val remoteResult = remoteDataSource.getCharacters()

        if (remoteResult is Result.Success) {
            localDataSource.deleteAllCharaters()
            remoteResult.data.forEach {
                localDataSource.saveCharacter(it)
            }
        } else if (remoteResult is Result.Error) {
            throw remoteResult.exception
        }
    }

}