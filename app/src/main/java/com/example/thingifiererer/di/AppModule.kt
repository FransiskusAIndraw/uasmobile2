package com.example.thingifiererer.di

import androidx.room.Room
import com.example.thingifiererer.database.AppDatabase
import com.example.thingifiererer.repository.UserRepository
import com.example.thingifiererer.viewmodel.AuthViewModel
import com.example.thingifiererer.viewmodel.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Menghapus tabel users jika ada perubahan skema
        database.execSQL("DROP TABLE IF EXISTS `users`")
        // Membuat tabel kembali
        database.execSQL("""
            CREATE TABLE `users` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `username` TEXT NOT NULL,
                `password` TEXT NOT NULL,
                `fullName` TEXT NOT NULL,
                `email` TEXT NOT NULL,
                `phoneNumber` TEXT NOT NULL
            )
        """)
    }
}

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single { get<AppDatabase>().userDao() }

    single { UserRepository(get()) }

    viewModel { AuthViewModel(get()) }
    viewModel { ProductViewModel() }
}
