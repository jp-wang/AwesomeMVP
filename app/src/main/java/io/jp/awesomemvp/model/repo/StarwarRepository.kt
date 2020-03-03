package io.jp.awesomemvp.model.repo

import androidx.lifecycle.LiveData
import io.jp.awesomemvp.model.data.Character
import io.jp.awesomemvp.model.data.Result

interface StarwarRepository {
    fun observeCharacters(): LiveData<Result<List<Character>>>

    suspend fun refreshCharacters()

    suspend fun getCharacter(id: String): Result<Character>
}