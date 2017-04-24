package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

/**
 * This is NOT an opmode.
 *
 * This class is used to define all the hardware for the OmniBot2.0.
 * This hardware class uses the following device names that have been configured on the robot phone:
 *
 * Front Left drive motor:    "motor_1"
 * Front Right drive motor:   "motor_2"
 * Back Left drive motor:     "motor_3"
 * Back Right drive motor:    "motor_4"
 *
 * Lift motor:                "motor_lift"
 *
 * Gyro sensor                "gyro_1"
 */

public class OmniBot2_1Hardware {
    //Public OpMode members
    public DcMotor motorFl   = null;
    public DcMotor motorFr   = null;
    public DcMotor motorBl   = null;
    public DcMotor motorBr   = null;

    //public DcMotor motorLift = null;

    public GyroSensor gyro   = null;

    //Local OpMode members
    HardwareMap hwMap        = null;

    //Constructor
    OmniBot2_1Hardware() {}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        motorFl =   hwMap.dcMotor.get("motor_1");
        motorFr =   hwMap.dcMotor.get("motor_2");  motorFr.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBl =   hwMap.dcMotor.get("motor_3");
        motorBr =   hwMap.dcMotor.get("motor_4");  motorBr.setDirection(DcMotorSimple.Direction.REVERSE);

        //motorLift = hwMap.dcMotor.get("motor_lift");

        gyro =      hwMap.gyroSensor.get("gyro_1");
    }
}