package cz.mendelu.pef.spatialhub.kiwitask.di

import com.google.gson.GsonBuilder
import cz.mendelu.pef.spatialhub.kiwitask.api.TopLocationsAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val networkModule = module {
    factory { provideInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideTopLocationAPI(get()) }
    single { provideRetrofit(get()) }
}

private const val API_URL =
    "https://api.skypicker.com/"

fun provideInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    return httpClient.addInterceptor(httpLoggingInterceptor).build()
}

fun provideTopLocationAPI(retrofit: Retrofit): TopLocationsAPI =
    retrofit.create(TopLocationsAPI::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
            )
        )
        .build()
}