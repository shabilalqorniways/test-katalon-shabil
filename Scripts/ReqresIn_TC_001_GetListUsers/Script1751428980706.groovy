import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Kirim request dan simpan response
def response = WS.sendRequest(OR.findTestObject('Object Repository/Web Service/ReqresIn/GET_ListUsers'))

// Ambil status code
def statusCode = response.getStatusCode()
KeywordUtil.logInfo("Status Code: " + statusCode)

// Verifikasi status code
assert statusCode == 200 : "Expected status code 200 but got: " + statusCode

// Parse response JSON
def responseText = response.getResponseText()
def json = new JsonSlurper().parseText(responseText)

// Ambil user pertama dari array data
def firstUser = json.data[0]

// Ambil ID dan email user pertama
def firstUserId = firstUser.id
def firstUserEmail = firstUser.email

// Tampilkan ke log
KeywordUtil.logInfo("First User ID: " + firstUserId)
KeywordUtil.logInfo("First User Email: " + firstUserEmail)