package cz.mendelu.pef.spatialhub.kiwitask.models

sealed class Result<out T: Any> {
    class Success<out T: Any>(val data: T): Result<T>()
    class Error(val message: String): Result<Nothing>()
    object Loading: Result<Nothing>()
    object Empty: Result<Nothing>()
}
