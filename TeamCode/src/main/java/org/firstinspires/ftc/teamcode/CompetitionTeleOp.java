package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Competition TeleOp", group = "Competition")
public class CompetitionTeleOp extends LinearOpMode {

    // DRIVE
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private ElapsedTime runtime = new ElapsedTime();

    // INTAKE
    private IntakeMode intake = new IntakeMode();

    // LAUNCHER
    private DcMotor launchMotor;

    private Gamepad.RumbleEffect customRumbleEffect;

    @Override
    public void runOpMode() {

        // ===== MAP DRIVE =====
        leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // ===== MAP INTAKE =====
        intake.init(hardwareMap);

        // ===== MAP LAUNCHER =====
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");

        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);


        customRumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(0.5, 0.5, 10)
                .build();

        telemetry.addLine("READY");
        telemetry.update();

        waitForStart();

        runtime.reset();


        while (opModeIsActive()) {


            // Read the vertical value of the left joystick
            // Negative sign corrects the joystick's inverted Y-axis
//            double drive = -gamepad1.left_stick_y;
//
//            // Apply the same power to both motors
//            // This moves the robot straight forward or backward
//            leftMotor.setPower(drive);
//            rightMotor.setPower(drive);

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);


            // =========================
            // INTAKE (left bumper hold)
            // =========================
            if (gamepad2.left_bumper) {
                intake.setMotorSpeed(1.0);
            } else {
                intake.setMotorSpeed(0);
            }

            // LAUNCHER (X hold)

            if (gamepad2.x) {
                launchMotor.setPower(-1.0);
                gamepad2.runRumbleEffect(customRumbleEffect);
            } else {
                launchMotor.setPower(0);
                gamepad2.stopRumble();
           }

            telemetry.addData("Drive Power", drive);
            telemetry.addData("Launcher", launchMotor.getPower());
            telemetry.update();
        }

        // ===== SAFE STOP =====
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        launchMotor.setPower(0);
        intake.setMotorSpeed(0);
        gamepad2.stopRumble();
    }
}
