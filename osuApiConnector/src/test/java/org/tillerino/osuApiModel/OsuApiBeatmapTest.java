package org.tillerino.osuApiModel;

import static org.tillerino.osuApiModel.Mods.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class OsuApiBeatmapTest {
	@Test
	public void testRegression() throws IOException {
		OsuApiBeatmap expected = new OsuApiBeatmap();

		expected.id=75; expected.setId=1; expected.artist="Kenji Ninuma"; expected.title="DISCO PRINCE"; expected.version="Normal"; expected.creator="peppy"; expected.source=""; expected.approved=1; expected.approvedDate=1191692791000l; expected.lastUpdate=1191692791000l; expected.bpm=119.999; expected.starDifficulty=2.2918; expected.overallDifficulty=6; expected.circleSize=4; expected.approachRate=6; expected.healthDrain=6; expected.hitLength=108; expected.totalLength=141; expected.mode=0;

		OsuApiBeatmap downloaded = new Downloader().getBeatmap(75, OsuApiBeatmap.class);

		assertEquals(expected, downloaded);
	}

	@Test
	public void testCalcOd() throws Exception {
		assertEquals(-6.5, OsuApiBeatmap.calcOd(0, getMask(HalfTime, Easy)), 1E-15);
		assertEquals(-2, OsuApiBeatmap.calcOd(6, getMask(HalfTime, Easy)), 1E-15);
		assertEquals(4, OsuApiBeatmap.calcOd(7, getMask(HalfTime)), 1E-15);
		assertEquals(8.5, OsuApiBeatmap.calcOd(10, getMask(HalfTime, HardRock)), 1E-15);
		assertEquals(5 + 2 / 3d, OsuApiBeatmap.calcOd(4, getMask(DoubleTime, Easy)), 1E-15);
		assertEquals(9 + 2 / 3d, OsuApiBeatmap.calcOd(8, getMask(DoubleTime)), 2E-15);
		assertEquals(10 + 1 / 3d, OsuApiBeatmap.calcOd(9, getMask(DoubleTime)), 2E-15);
		assertEquals(11, OsuApiBeatmap.calcOd(10, getMask(DoubleTime, HardRock)), 1E-2);
	}

	@Test
	public void testMsToAr() {
		assertEquals(-7.5, OsuApiBeatmap.msToAr(2700), 1E-15);
		assertEquals(0, OsuApiBeatmap.msToAr(1800), 1E-15);
		assertEquals(2, OsuApiBeatmap.msToAr(1560), 1E-15);
		assertEquals(5, OsuApiBeatmap.msToAr(1200), 1E-15);
		assertEquals(7, OsuApiBeatmap.msToAr(900), 1E-15);
		assertEquals(10, OsuApiBeatmap.msToAr(450), 1E-15);
		assertEquals(11, OsuApiBeatmap.msToAr(300), 1E-15);
	}

	@Test
	public void testOdToMs() throws Exception {
		assertEquals(78, OsuApiBeatmap.odToMs(0), 1E-15);
		assertEquals(48, OsuApiBeatmap.odToMs(5), 1E-15);
		assertEquals(18, OsuApiBeatmap.odToMs(10), 1E-15);
		assertEquals(12, OsuApiBeatmap.odToMs(11), 1E-15);
		assertEquals(52, OsuApiBeatmap.odToMs(4 + 1 / 3d), 1E-15);
		assertEquals(85.5, OsuApiBeatmap.odToMs(-1.25), 1E-15);
		assertEquals(117, OsuApiBeatmap.odToMs(-6.5), 1E-15);
	}

	@Test
	public void testCalcAR() throws Exception {
		assertEquals(-7.5, OsuApiBeatmap.calcAR(0, getMask(HalfTime, Easy)), 1E-15);
		assertEquals(-3, OsuApiBeatmap.calcAR(6, getMask(HalfTime, Easy)), 1E-15);
		assertEquals(3.75, OsuApiBeatmap.calcAR(7, getMask(HalfTime)), 1E-15);
		assertEquals(8.5, OsuApiBeatmap.calcAR(10, getMask(HalfTime, HardRock)), 1E-15);
		assertEquals(6 + 2 / 30d, OsuApiBeatmap.calcAR(4, getMask(DoubleTime, Easy)), 1E-15);
		assertEquals(9 + 2 / 3d, OsuApiBeatmap.calcAR(8, getMask(DoubleTime)), 2E-15);
		assertEquals(10 + 1 / 3d, OsuApiBeatmap.calcAR(9, getMask(DoubleTime)), 2E-15);
		assertEquals(11, OsuApiBeatmap.calcAR(10, getMask(DoubleTime, HardRock)), 0);
	}

	@Test
	public void testArToMs() {
		assertEquals(2700, OsuApiBeatmap.arToMs(-7.5), 1E-15);
		assertEquals(1800, OsuApiBeatmap.arToMs(0), 1E-15);
		assertEquals(1560, OsuApiBeatmap.arToMs(2), 1E-15);
		assertEquals(1200, OsuApiBeatmap.arToMs(5), 1E-15);
		assertEquals(900, OsuApiBeatmap.arToMs(7), 1E-15);
		assertEquals(450, OsuApiBeatmap.arToMs(10), 1E-15);
		assertEquals(300, OsuApiBeatmap.arToMs(11), 1E-15);
	}

	@Test
	public void testMsToOd() throws Exception {
		assertEquals(0, OsuApiBeatmap.msToOd(78), 1E-15);
		assertEquals(5, OsuApiBeatmap.msToOd(48), 1E-15);
		assertEquals(10, OsuApiBeatmap.msToOd(18), 1E-15);
		assertEquals(11, OsuApiBeatmap.msToOd(12), 1E-15);
		assertEquals(4 + 1 / 3d, OsuApiBeatmap.msToOd(52), 1E-15);
		assertEquals(-1.25, OsuApiBeatmap.msToOd(85.5), 1E-15);
		assertEquals(-6.5, OsuApiBeatmap.msToOd(117), 1E-15);
	}
}
