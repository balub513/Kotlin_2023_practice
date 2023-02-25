package com.example.test2023app.other

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SampleOneMockito {

    @Mock
    lateinit var mockedList: ArrayList<String>

    @Spy
    lateinit var spiedList: ArrayList<String>

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<String>

    //Inject Mock

    @Mock
    lateinit var map : MutableMap<String, String>

    @InjectMocks
    lateinit var dictionary: MyDictionary

    @Test
    fun whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("Balu")
        mockedList.add("Balli")
        Mockito.verify(mockedList).add("Balu")
        Mockito.verify(mockedList).add("Balli")
        assertEquals(mockedList.size, 0)   // --->> 0 means not on real objects

        Mockito.`when`(mockedList.size).thenReturn(10)
        assertEquals(mockedList.size, 10)
    }

    @Test
    fun whenNotUseSpyAnnotation_thenCorrect() {
        spiedList.add("Balu")
        spiedList.add("Balli")
        Mockito.verify(spiedList).add("Balu")
        Mockito.verify(spiedList).add("Balli")
        assertEquals(spiedList.size, 2) // --->> 2 means  on real objects

        Mockito.doReturn(10).`when`(spiedList).size
        assertEquals(spiedList.size, 10)
    }

    @Test
    fun whenUseCaptorAnnotation_thenTheSame() {
        mockedList.add("Ram")
        Mockito.verify(mockedList).add(argumentCaptor.capture())
        assertEquals(argumentCaptor.value, "Ram")   // -->> to test the Arguments
    }

    @Test
    fun whenUseInjectMocksAnnotation_thenCorrect(){
        Mockito.`when`(map["month"]).thenReturn("September")

       assertEquals(dictionary.getMeaning("month"),"September")
    }
}


class MyDictionary {

    var wordMap: MutableMap<String, String> = HashMap()

    fun add(word: String, meaning: String) {
        wordMap[word] = meaning
    }

    fun getMeaning(word: String): String? {
        return wordMap[word]
    }
}