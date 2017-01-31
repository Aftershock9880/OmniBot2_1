package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import static com.qualcomm.robotcore.util.Range.clip;

@TeleOp(name = "OmniBot Gyro Teleop", group = "OmniBot")
//@Disabled
public class OmniBot2_1PIDGyroTeleop extends OpMode {

    private OmniBot2_1Hardware robot = new OmniBot2_1Hardware();

	private double powerDivider = 1; //Divide power by this much

    int direction = 0;
    double lastError = 0;

    double Flpower;
    double Frpower;
    double Blpower;
    double Brpower;

    double kp = 1/600;
    //double ki = 1/600;
    double kd = 1/600;

    double p = 0;
    //double i = 0;
    double d = 0;

    double finalPD = 0;

	@Override
	public void init() {
        robot.init(hardwareMap);

    }

    //@Override
    //public void start() {}

	@Override
	public void loop() {

        /*
		if (gamepad1.dpad_up){ //If you press the up d-pad, change sensitivity
			powerDivider = powerDivider + 0.005;
		}
        if (gamepad1.dpad_down) {
			powerDivider = powerDivider - 0.005;
		}
        */

        // movement code, Gamepad 1 controls movement with left stick and turning with right stick
        if (powerDivider <= 1) {
			powerDivider = 1;
		}

        Flpower =+ -gamepad1.left_stick_y +  gamepad1.left_stick_x +  gamepad1.right_stick_x;
        Frpower =+ -gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x;
        Blpower =+ -gamepad1.left_stick_y + -gamepad1.left_stick_x +  gamepad1.right_stick_x;
        Brpower =+ -gamepad1.left_stick_y +  gamepad1.left_stick_x + -gamepad1.right_stick_x;

        Flpower = clip(Flpower, -0.7, 0.7);
        Frpower = clip(Frpower, -0.7, 0.7);
        Blpower = clip(Blpower, -0.7, 0.7);
        Brpower = clip(Brpower, -0.7, 0.7);

        if (gamepad1.right_stick_x > 0.001 || gamepad1.right_stick_x < -0.001) {
                direction = robot.gyro.getHeading();
        }

        p = robot.gyro.getHeading() - direction;
        //i =+ p;
        d = p - lastError;
        lastError = p;
        finalPD = p * kp + d * kd;

        Flpower =+ finalPD;
        Frpower =+ -finalPD;
        Blpower =+ finalPD;
        Brpower =+ -finalPD;

        robot.motorFl.setPower(Flpower);
        robot.motorFr.setPower(Frpower);
        robot.motorBl.setPower(Blpower);
        robot.motorBr.setPower(Brpower);

        if (gamepad1.right_stick_x > 0.001 || gamepad1.right_stick_x < -0.001) {
            direction = robot.gyro.getHeading();
        }

		//Send telemetry data back to driver station.
		telemetry.addData("left stick X: ", -gamepad1.left_stick_x);
		telemetry.addData("left stick Y: ", -gamepad1.left_stick_y);
        telemetry.addData("right stick Y: ", -gamepad1.right_stick_y);
		telemetry.addData("power divider: ", powerDivider);
        telemetry.addData("gyro heading: ", robot.gyro.getHeading());
        telemetry.addData(": ", 1);
        telemetry.addData("PD loop addition: ", finalPD);
	}
}