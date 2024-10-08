package io.github.thibaultbee.srtdroid.core.models

import io.github.thibaultbee.srtdroid.core.enums.SockOpt
import io.github.thibaultbee.srtdroid.core.enums.Transtype
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SrtUrlTest {
    @Test
    fun srtUrlWithBoolean() {
        var srtUrl = SrtUrl("srt://host:9000?tsbpd=0")
        assertEquals(false, srtUrl.enableTimestampBasedPacketDelivery)
        srtUrl = SrtUrl("srt://host:9000?tsbpd=1")
        assertEquals(true, srtUrl.enableTimestampBasedPacketDelivery)
    }

    @Test
    fun srtUrlWithNumber() {
        val srtUrl = SrtUrl("srt://host:9000?snddropdelay=2000")
        assertEquals(2, srtUrl.senderDropDelayInMs)
    }

    @Test
    fun srtUrlWithString() {
        val srtUrl = SrtUrl("srt://host:9000?streamid=abcde")
        assertEquals("abcde", srtUrl.streamId)
    }

    @Test
    fun srtUrlWithTranstype() {
        var srtUrl = SrtUrl("srt://host:9000?transtype=live")
        assertEquals(Transtype.LIVE, srtUrl.transtype)
        srtUrl = SrtUrl("srt://host:9000?transtype=file")
        assertEquals(Transtype.FILE, srtUrl.transtype)
    }

    @Test
    fun srtUrlWithMultipleParameters() {
        val srtUrl =
            SrtUrl("srt://host:9000?transtype=live&srt_streamid=abcde&rcvlatency=2000&passphrase=1234")
        assertEquals(Transtype.LIVE, srtUrl.transtype)
        assertEquals("abcde", srtUrl.streamId)
        assertEquals(2, srtUrl.receiverLatencyInMs)
        assertEquals("1234", srtUrl.passphrase)
    }

    @Test
    fun srtUrlWithStreamId() {
        var srtUrl =
            SrtUrl("srt://host:9000?srt_streamid=abcde")
        assertEquals("abcde", srtUrl.streamId)

        srtUrl =
            SrtUrl("srt://host:9000?streamid=abcde")
        assertEquals("abcde", srtUrl.streamId)
    }

    @Test
    fun srtUrlWithStreamIdFormat() {
        var srtUrl =
            SrtUrl("srt://host:9000?srt_streamid=#!::u=admin,r=bluesbrothers1_hi")
        assertEquals("#!::u=admin,r=bluesbrothers1_hi", srtUrl.streamId)

        srtUrl =
            SrtUrl("srt://host:9000?streamid=#!::u=admin,r=bluesbrothers1_hi")
        assertEquals("#!::u=admin,r=bluesbrothers1_hi", srtUrl.streamId)

        srtUrl =
            SrtUrl("srt://host:9000?streamid=#!::u=admin,r=bluesbrothers1_hi&transtype=live")
        assertEquals("#!::u=admin,r=bluesbrothers1_hi", srtUrl.streamId)
        assertEquals(Transtype.LIVE, srtUrl.transtype)
    }

    @Test
    fun srtUrlApplyToSocket() {
        val srtUrl = SrtUrl(hostname = "127.0.0.1", port = 9000, connectTimeoutInMs = 1234)
        val socket = SrtSocket()
        srtUrl.preApplyTo(socket)
        assertEquals(1234, socket.getSockFlag(SockOpt.CONNTIMEO))
    }

    @Test
    fun srtUrlToUri() {
        val srtUrl = SrtUrl(hostname = "127.0.0.1", port = 9000, connectTimeoutInMs = 1234)
        val uri = srtUrl.srtUri
        assertEquals(1234, uri.getQueryParameter("connect_timeout")?.toInt())
    }

    @Test
    fun srtUrlWithStreamIdFormatToUri() {
        val srtUrl = SrtUrl(
            hostname = "127.0.0.1",
            port = 9000,
            streamId = "#!::u=admin,r=bluesbrothers1_hi"
        )
        val uri = srtUrl.srtUri
        assertTrue(uri.toString().contains("streamid=#!::u=admin,r=bluesbrothers1_hi"))
    }
}