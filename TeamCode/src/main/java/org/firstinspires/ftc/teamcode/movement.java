package org.firstinspires.ftc.teamcode;

// Import FTC OpMode and hardware classes
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

// Declare this OpMode as a TeleOp program
@TeleOp(name = "Tank Drive Forward Backward", group = "TeleOp")
public class movement extends LinearOpMode {

    // Declare motor variables
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {

        // Map motors from the robot configuration
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");

        // Reverse one motor so both wheels move forward together
        // (Motors are mounted in opposite directions on the robot)
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the driver to press PLAY
        waitForStart();

        // Run continuously while the OpMode is active
        while (opModeIsActive()) {

            // Read the vertical value of the left joystick
            // Negative sign corrects the joystick's inverted Y-axis
            double drive = -gamepad1.left_stick_y;

            // Apply the same power to both motors
            // This moves the robot straight forward or backward
            leftMotor.setPower(drive);
            rightMotor.setPower(drive);
        }
    }
}


