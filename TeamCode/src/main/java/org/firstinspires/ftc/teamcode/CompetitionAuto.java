package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Competition Auto", group = "Competition")
public class CompetitionAuto extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;


    @Override
    public void runOpMode() {


        // MAP DRIVE MOTORS

        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");


        // Reverse Motors to match TeleOp Direction

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Safe stopping

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("AUTO READY");
        telemetry.update();


        waitForStart();

        if (!opModeIsActive()) return;

        // DRIVE FORWARD TO LEAVE ZONE

        leftMotor.setPower(0.7);
        rightMotor.setPower(0.7);

        sleep(3000);

        // STOP
        leftMotor.setPower(0);
        rightMotor.setPower(0);


    }
}
