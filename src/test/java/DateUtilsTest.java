import com.akamai.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilsTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void timestampEarlierThanNow_IllegalArgumentException(){
        //given
        DateUtils dateUtils = new DateUtils();

        //then
        dateUtils.getSecondDiffFromNow(LocalDateTime.now()
                .minusMinutes(1)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void timestampAfterNow_ValidAmountOfSecondsReturned(){
        //given
        DateUtils dateUtils = new DateUtils();

        //when
        long seconds=dateUtils.getSecondDiffFromNow(LocalDateTime.now()
                .plusMinutes(1)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        //then
        Assert.assertNotEquals(seconds,0);
    }

    @Test
    public void getTimestampByBeginningAndDiff_ValidTimeStampReturned(){
        //given
        DateUtils dateUtils = new DateUtils();
        String baseTimestamp = LocalDateTime
                .of(2018,1,18,5,10,40)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //when
        String timestamp=dateUtils.getTimestampByBeginningAndDiff(LocalDateTime
                .of(2018,1,18,5,10,20)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),20);

        //then
        Assert.assertEquals(baseTimestamp,timestamp);
    }
}
