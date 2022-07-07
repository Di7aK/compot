package com.di7ak.compot.data

import android.content.Context
import androidx.room.Room
import com.di7ak.compot.data.database.AppDatabase
import com.di7ak.compot.data.datasource.TodoDataSource
import com.di7ak.compot.data.repository.TodoRepositoryImpl
import com.di7ak.compot.domain.repositories.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideTodoDataSource(database: AppDatabase): TodoDataSource = database.getTodoDao()

    @Provides
    fun provideTodoRepository(dataSource: TodoDataSource): TodoRepository = TodoRepositoryImpl(dataSource)
}