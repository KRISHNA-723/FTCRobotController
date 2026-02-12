package org.firstinspires.ftc.teamcode;

// Import required FTC classes
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

// Declare this class as a TeleOp OpMode
@TeleOp(name = "Omni / Mecanum Drive", group = "TeleOp")
public class mechanum extends LinearOpMode {

    // Declare motor variables for each wheel
    DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() {

        // Map motors from the robot configuration file
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft   = hardwareMap.get(DcMotor.class, "backLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");

        // Reverse the left motors so all wheels move forward together
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait until the driver presses PLAY
        waitForStart();

        // Run this loop while the OpMode is active
        while (opModeIsActive()) {

            // Read joystick values
            // Left stick Y controls forward/backward (negated due to joystick orientation)
            double y = -gamepad1.left_stick_y;

            // Left stick X controls strafing left/right
            double x = gamepad1.left_stick_x;

            // Right stick X controls rotation
            double rx = gamepad1.right_stick_x;

            // Calculate motor power values for mecanum drive
            double frontLeftPower  = y + x + rx;
            double backLeftPower   = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower  = y + x - rx;

            // Send calculated power values to the motors
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
        }
    }
}

