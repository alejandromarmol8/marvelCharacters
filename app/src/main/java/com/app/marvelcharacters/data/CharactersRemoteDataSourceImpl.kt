package com.app.marvelcharacters.data

import com.app.marvelcharacters.data.ParsedResponse.*
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersResultFailure.*
import com.app.marvelcharacters.domain.CommonFailures.*
import java.net.UnknownHostException

class CharactersRemoteDataSourceImpl(private val retrofit: CharactersRetrofit): CharactersRemoteDataSource {
    override suspend fun getCharacters() : ParsedResponse<CommonErrors, List<CharacterData>>{
        return try {
            Success(retrofit.getCharacters().data.results)
        }catch (e: UnknownHostException) {
            Error(CommonErrors(NoInternet))
        } catch (e: Throwable) {
            Error(CommonErrors(Unknown))
        }
    }

    override suspend fun getCharacter(character: String): ParsedResponse<CommonErrors, CharacterDetailData> {
        return try {
            val characterData = retrofit.getCharacter(character).data
            if(characterData.results.size > 1) Error(CommonErrors(Unknown))
            Success(characterData.results[0])
        } catch (e: UnknownHostException) {
            Error(CommonErrors(NoInternet))
        } catch (e: Throwable) {
            Error(CommonErrors(Unknown))
        }
    }
}