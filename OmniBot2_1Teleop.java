package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.qualcomm.robotcore.util.Range.clip;

@TeleOp(name = "OmniBot 2.1 Teleop", group = "OmniBot 2.1")
//@Disabled
public class OmniBot2_1Teleop extends OpMode {

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
        if(powerDivider <= 1){
			powerDivider = 1;
		}

        Flpower = -gamepad1.left_stick_y +  gamepad1.left_stick_x +  gamepad1.right_stick_x;
        Frpower = -gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x;
        Blpower = -gamepad1.left_stick_y + -gamepad1.left_stick_x +  gamepad1.right_stick_x;
        Brpower = -gamepad1.left_stick_y +  gamepad1.left_stick_x + -gamepad1.right_stick_x;

        Flpower = clip(Flpower, -0.7, 0.7);
        Frpower = clip(Frpower, -0.7, 0.7);
        Blpower = clip(Blpower, -0.7, 0.7);
        Brpower = clip(Brpower, -0.7, 0.7);

		robot.motorFl.setPower(Flpower /*powerDivider*/);
		robot.motorFr.setPower(Frpower /*powerDivider*/);
		robot.motorBl.setPower(Blpower /*powerDivider*/);
		robot.motorBr.setPower(Brpower /*powerDivider*/);

        // lift code
        if (gamepad1.y) {
            robot.motorLift.setPower(1);
        }
        else if (gamepad1.a) {
            robot.motorLift.setPower(-1);
        }
        else {
            robot.motorLift.setPower(0);
        }

		//Send telemetry data back to driver station.
		telemetry.addData("stick X: ", -gamepad1.left_stick_x);
		telemetry.addData("stick Y: ", -gamepad1.left_stick_y);
		telemetry.addData("power divider:", powerDivider);
	}
}