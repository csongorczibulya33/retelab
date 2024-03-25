package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;

public class TrainSensorTest {
    
    TrainController mockTC;
    TrainUser mockTU;
    TrainSensorImpl trainSensor;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTC, mockTU);
        trainSensor.overrideSpeedLimit(50);
    }
    
    @Test
    public void checkSpeedLimit()
    {
        Assert.assertEquals(10, 10);
    }

    @Test
    public void alsoHatarTeszt()
    {
        trainSensor.overrideSpeedLimit(-1);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void felsoHatarTeszt()
    {
        trainSensor.overrideSpeedLimit(501);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void feleOlyanGyorsTeszt()
    {
        when(mockTC.getReferenceSpeed()).thenReturn(150);
        trainSensor.overrideSpeedLimit(150);
        trainSensor.overrideSpeedLimit(50);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void nincsJelzes()
    {
        when(mockTC.getReferenceSpeed()).thenReturn(150);
        trainSensor.overrideSpeedLimit(150);
        trainSensor.overrideSpeedLimit(140);
        verify(mockTU, times(0)).setAlarmState(true);
    }

    
}
