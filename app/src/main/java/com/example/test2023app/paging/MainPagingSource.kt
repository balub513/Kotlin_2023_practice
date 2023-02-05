package com.example.test2023app.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
//https://genicsblog.com/gouravkhunger/pagination-in-android-room-database-using-the-paging-3-library
class MainPagingSource(private val dao: ItemDao) : PagingSource<Int, Item>() {
    /*
    The getRefreshKey() method of the PagingSource class is used to get the key of the page that will
    be passed into the params for load() function. This is calculated on subsequent refreshes/invalidation
     of the data after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /*
    The load() function gets details about the page we need to load, loads it, and returns the page
     with the needed meta-data. It also handles errors that might occur.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 0

        return try {
            val entities = dao.getPagedList(params.loadSize, page * params.loadSize)
            if (page != 0) delay(1000)
            LoadResult.Page(
                data = entities, prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}