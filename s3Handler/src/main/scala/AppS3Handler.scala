
import software.amazon.awssdk.services.s3.S3Client

trait AppS3Handler {

  protected def objectExist(s3Client: S3Client,bucketKey: String,objectKey: String ): Boolean
  protected def writeObject(s3Client: S3Client,bucketKey: String,objectKey: String )
}
