package tv.mosobr.news.di

import retrofit2.Response
import tv.mosobr.news.tools.Logger
import java.io.IOException

/**
 * Базовый разбор ответа сервера
 */
class ApiResponse<T> {

    private val code: Int

    val body: T?

    val errorMessage: String?

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()?.string()
                } catch (ignored: IOException) {
                    Logger.e(ignored)
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }
}