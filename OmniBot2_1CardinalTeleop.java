package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static com.qualcomm.robotcore.util.Range.clip;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

@TeleOp(name = "OmniBot Teleop", group = "OmniBot")
//@Disabled
public class OmniBot2_1CardinalTeleop extends OpMode {

    private OmniBot2_1Hardware robot = new OmniBot2_1Hardware();

	private double powerDivider = 1; //Divide power by this much

    double Flpower;
    double Frpower;
    double Blpower;
    double Brpower;

	@Override
	public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void start() {}

	@Override
	public void loop() {
        double joyX = -gamepad1.left_stick_x;
        double joyY = -gamepad1.left_stick_y;
        double joyTheta  = atan2(joyY, joyX);
        double joyRadius = sqrt((joyX*joyX) + (joyY*joyY));

        double robotDir  = robot.gyro.getRotationFraction() + 45;

        // movement code, Gamepad 1 controls movement with left stick and turning with right stick
        Flpower =  cos(joyTheta + robotDir) * joyRadius;
        Frpower = -sin(joyTheta + robotDir) * joyRadius;
        Blpower = -sin(joyTheta + robotDir) * joyRadius;
        Brpower =  cos(joyTheta + robotDir) * joyRadius;

		robot.motorFl.setPower(Flpower);
		robot.motorFr.setPower(Frpower);
		robot.motorBl.setPower(Blpower);
		robot.motorBr.setPower(Brpower);

		//Send telemetry data back to driver station.
		telemetry.addData("stick X: ", -gamepad1.left_stick_x);
		telemetry.addData("stick Y: ", -gamepad1.left_stick_y);
        telemetry.addData("stick Theta: ",  joyTheta );
		telemetry.addData("stick Radius: ", joyRadius);
        telemetry.addData("Robot direction: ", robot.gyro.getRotationFraction());
	}
}
