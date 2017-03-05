package pl.wendigo.chrome.domain.storage

/**
 * StorageDomain represents remote debugger protocol domain.
 *
 * This class was autogenerated by main.go. Do not edit :)
 */
@pl.wendigo.chrome.ProtocolExperimental class StorageDomain internal constructor(private val connection : pl.wendigo.chrome.RemoteChromeConnection) {

	/**
	 * Clears storage for origin.
	 */
	fun clearDataForOrigin(input : ClearDataForOriginRequest) : io.reactivex.Flowable<pl.wendigo.chrome.GenericResponse> {
        return connection.runAndCaptureResponse("Storage.clearDataForOrigin", input, pl.wendigo.chrome.GenericResponse::class.java)
	}

  }

data class ClearDataForOriginRequest (
    /**
     * Security origin.
     */
    val origin : String,

    /**
     * Comma separated origin names.
     */
    val storageTypes : String

)



