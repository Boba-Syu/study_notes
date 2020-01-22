package cn.bobasyu.amazon.ec2;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 亚马逊EC2服务器的API学习
 *
 * @author Boba
 */
@Slf4j
public class AmazonApiTest {
    public static void main(String[] args) {
        AmazonEC2 ec2 = getEc2s("", "");
        printInstanceList(ec2);

        AmazonS3 s3 = getS3("", "", "");
        System.out.println(s3.getBucketAcl(""));
    }

    /**
     * 打印Amazon EC2下面的所有instance
     *
     * @param ec2 需要操作的EC2实例
     */
    public static void printInstanceList(AmazonEC2 ec2) {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        DescribeInstancesResult response = ec2.describeInstances(request);
        List<Reservation> reservations = response.getReservations();
        reservations.forEach(reservation -> reservation.getInstances()
                .forEach(instance -> log.info(instance.getInstanceId() + "("
                                + instance.getPublicIpAddress() + ") "
                                + instance.getInstanceType() + ": "
                                + instance.getState().getName()
                        )
                )
        );
    }

    /**
     * 根据密钥获取EC2实例
     *
     * @param accessKey accessKey
     * @param secretKey secretKey
     * @return EC2实例
     */
    public static AmazonEC2 getEc2s(String accessKey, String secretKey) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonEC2ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();
    }

    /**
     * 获取AmazonS3实例对象
     *
     * @param keyId       ketId
     * @param secretKeyId secretKeyId
     * @param region      实例服务器所在地址
     * @return S3实例
     */
    public static AmazonS3 getS3(String keyId, String secretKeyId, String region) {
        AWSCredentials credentials = new BasicAWSCredentials(keyId, secretKeyId);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }
}
