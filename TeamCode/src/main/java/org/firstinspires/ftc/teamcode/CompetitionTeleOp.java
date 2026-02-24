package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Competition TeleOp", group = "Competition")
public class CompetitionTeleOp extends LinearOpMode {

    // DRIVE
    private DcMotor leftMotor;
    private DcMotor rightMotor;


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

        while (opModeIsActive()) {

            // =========================
            // DRIVE (tank forward/back)
            // =========================
            double drive = -gamepad1.left_stick_y;
            leftMotor.setPower(drive);
            rightMotor.setPower(drive);

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
                launchMotor.setPower(1.0);
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
