package pl.wendigo.chrome.domain.network

/*
 * Unique loader identifier.
 */
typealias LoaderId = String

/*
 * Unique request identifier.
 */
typealias RequestId = String

/*
 * Number of seconds since epoch.
 */

typealias Timestamp = Double

/*
 * Request / response headers as keys / values of JSON object.
 */

typealias Headers = Map<String, Any>

/*
 * Loading priority of a resource request.
 */
typealias ConnectionType = String

/*
 * Represents the cookie&apos;s &apos;SameSite&apos; status: https://tools.ietf.org/html/draft-west-first-party-cookies
 */
typealias CookieSameSite = String

/*
 * Timing information for the request.
 */

data class ResourceTiming(
  /**
   * Timing's requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime.
   */
  val requestTime : Double,

  /**
   * Started resolving proxy.
   */
  val proxyStart : Double,

  /**
   * Finished resolving proxy.
   */
  val proxyEnd : Double,

  /**
   * Started DNS address resolve.
   */
  val dnsStart : Double,

  /**
   * Finished DNS address resolve.
   */
  val dnsEnd : Double,

  /**
   * Started connecting to the remote host.
   */
  val connectStart : Double,

  /**
   * Connected to the remote host.
   */
  val connectEnd : Double,

  /**
   * Started SSL handshake.
   */
  val sslStart : Double,

  /**
   * Finished SSL handshake.
   */
  val sslEnd : Double,

  /**
   * Started running ServiceWorker.
   */
  @pl.wendigo.chrome.ProtocolExperimental val workerStart : Double,

  /**
   * Finished Starting ServiceWorker.
   */
  @pl.wendigo.chrome.ProtocolExperimental val workerReady : Double,

  /**
   * Started sending request.
   */
  val sendStart : Double,

  /**
   * Finished sending request.
   */
  val sendEnd : Double,

  /**
   * Time the server started pushing request.
   */
  @pl.wendigo.chrome.ProtocolExperimental val pushStart : Double,

  /**
   * Time the server finished pushing request.
   */
  @pl.wendigo.chrome.ProtocolExperimental val pushEnd : Double,

  /**
   * Finished receiving response headers.
   */
  val receiveHeadersEnd : Double

)

/*
 * Loading priority of a resource request.
 */
typealias ResourcePriority = String

/*
 * HTTP request data.
 */

data class Request(
  /**
   * Request URL.
   */
  val url : String,

  /**
   * HTTP request method.
   */
  val method : String,

  /**
   * HTTP request headers.
   */
  val headers : Headers,

  /**
   * HTTP POST request data.
   */
  val postData : String? = null,

  /**
   * The mixed content status of the request, as defined in http://www.w3.org/TR/mixed-content/
   */
  val mixedContentType : String? = null,

  /**
   * Priority of the resource request at the time request is sent.
   */
  val initialPriority : ResourcePriority,

  /**
   * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
   */
  val referrerPolicy : String

)

/*
 * Details of a signed certificate timestamp (SCT).
 */

data class SignedCertificateTimestamp(
  /**
   * Validation status.
   */
  val status : String,

  /**
   * Origin.
   */
  val origin : String,

  /**
   * Log name / description.
   */
  val logDescription : String,

  /**
   * Log ID.
   */
  val logId : String,

  /**
   * Issuance date.
   */
  val timestamp : Timestamp,

  /**
   * Hash algorithm.
   */
  val hashAlgorithm : String,

  /**
   * Signature algorithm.
   */
  val signatureAlgorithm : String,

  /**
   * Signature data.
   */
  val signatureData : String

)

/*
 * Security details about a request.
 */

data class SecurityDetails(
  /**
   * Protocol name (e.g. "TLS 1.2" or "QUIC").
   */
  val protocol : String,

  /**
   * Key Exchange used by the connection, or the empty string if not applicable.
   */
  val keyExchange : String,

  /**
   * (EC)DH group used by the connection, if applicable.
   */
  val keyExchangeGroup : String? = null,

  /**
   * Cipher name.
   */
  val cipher : String,

  /**
   * TLS MAC. Note that AEAD ciphers do not have separate MACs.
   */
  val mac : String? = null,

  /**
   * Certificate ID value.
   */
  val certificateId : pl.wendigo.chrome.domain.security.CertificateId,

  /**
   * Certificate subject name.
   */
  val subjectName : String,

  /**
   * Subject Alternative Name (SAN) DNS names and IP addresses.
   */
  val sanList : Array<String>,

  /**
   * Name of the issuing CA.
   */
  val issuer : String,

  /**
   * Certificate valid from date.
   */
  val validFrom : Timestamp,

  /**
   * Certificate valid to (expiration) date
   */
  val validTo : Timestamp,

  /**
   * List of signed certificate timestamps (SCTs).
   */
  val signedCertificateTimestampList : Array<SignedCertificateTimestamp>

)

/*
 * The reason why request was blocked.
 */
typealias BlockedReason = String

/*
 * HTTP response data.
 */

data class Response(
  /**
   * Response URL. This URL can be different from CachedResource.url in case of redirect.
   */
  val url : String,

  /**
   * HTTP response status code.
   */
  val status : Double,

  /**
   * HTTP response status text.
   */
  val statusText : String,

  /**
   * HTTP response headers.
   */
  val headers : Headers,

  /**
   * HTTP response headers text.
   */
  val headersText : String? = null,

  /**
   * Resource mimeType as determined by the browser.
   */
  val mimeType : String,

  /**
   * Refined HTTP request headers that were actually transmitted over the network.
   */
  val requestHeaders : Headers? = null,

  /**
   * HTTP request headers text.
   */
  val requestHeadersText : String? = null,

  /**
   * Specifies whether physical connection was actually reused for this request.
   */
  val connectionReused : Boolean,

  /**
   * Physical connection id that was actually used for this request.
   */
  val connectionId : Double,

  /**
   * Remote IP address.
   */
  @pl.wendigo.chrome.ProtocolExperimental val remoteIPAddress : String? = null,

  /**
   * Remote port.
   */
  @pl.wendigo.chrome.ProtocolExperimental val remotePort : Int? = null,

  /**
   * Specifies that the request was served from the disk cache.
   */
  val fromDiskCache : Boolean? = null,

  /**
   * Specifies that the request was served from the ServiceWorker.
   */
  val fromServiceWorker : Boolean? = null,

  /**
   * Total number of bytes received for this request so far.
   */
  val encodedDataLength : Double,

  /**
   * Timing information for the given request.
   */
  val timing : ResourceTiming? = null,

  /**
   * Protocol used to fetch this request.
   */
  val protocol : String? = null,

  /**
   * Security state of the request resource.
   */
  val securityState : pl.wendigo.chrome.domain.security.SecurityState,

  /**
   * Security details for the request.
   */
  val securityDetails : SecurityDetails? = null

)

/*
 * WebSocket request data.
 */

data class WebSocketRequest(
  /**
   * HTTP request headers.
   */
  val headers : Headers

)

/*
 * WebSocket response data.
 */

data class WebSocketResponse(
  /**
   * HTTP response status code.
   */
  val status : Double,

  /**
   * HTTP response status text.
   */
  val statusText : String,

  /**
   * HTTP response headers.
   */
  val headers : Headers,

  /**
   * HTTP response headers text.
   */
  val headersText : String? = null,

  /**
   * HTTP request headers.
   */
  val requestHeaders : Headers? = null,

  /**
   * HTTP request headers text.
   */
  val requestHeadersText : String? = null

)

/*
 * WebSocket frame data.
 */

data class WebSocketFrame(
  /**
   * WebSocket frame opcode.
   */
  val opcode : Double,

  /**
   * WebSocke frame mask.
   */
  val mask : Boolean,

  /**
   * WebSocke frame payload data.
   */
  val payloadData : String

)

/*
 * Information about the cached resource.
 */

data class CachedResource(
  /**
   * Resource URL. This is the url of the original network request.
   */
  val url : String,

  /**
   * Type of this resource.
   */
  val type : pl.wendigo.chrome.domain.page.ResourceType,

  /**
   * Cached response data.
   */
  val response : Response? = null,

  /**
   * Cached response body size.
   */
  val bodySize : Double

)

/*
 * Information about the request initiator.
 */

data class Initiator(
  /**
   * Type of this initiator.
   */
  val type : String,

  /**
   * Initiator JavaScript stack trace, set for Script only.
   */
  val stack : pl.wendigo.chrome.domain.runtime.StackTrace? = null,

  /**
   * Initiator URL, set for Parser type only.
   */
  val url : String? = null,

  /**
   * Initiator line number, set for Parser type only (0-based).
   */
  val lineNumber : Double? = null

)

/*
 * Cookie object
 */

data class Cookie(
  /**
   * Cookie name.
   */
  val name : String,

  /**
   * Cookie value.
   */
  val value : String,

  /**
   * Cookie domain.
   */
  val domain : String,

  /**
   * Cookie path.
   */
  val path : String,

  /**
   * Cookie expiration date as the number of seconds since the UNIX epoch.
   */
  val expires : Double,

  /**
   * Cookie size.
   */
  val size : Int,

  /**
   * True if cookie is http-only.
   */
  val httpOnly : Boolean,

  /**
   * True if cookie is secure.
   */
  val secure : Boolean,

  /**
   * True in case of session cookie.
   */
  val session : Boolean,

  /**
   * Cookie SameSite type.
   */
  val sameSite : CookieSameSite? = null

)
