import boto3
import botocore.config
import json
from datetime import datetime

def blog_generate_uding_bedrock(blogtopic:str)->str:
    prompt=f"""<s>[INST]Human: Write a 200 word blog on the topic {blogtopic}
    Assistant:[/INST]</s>
    """
    body={
        "modelId": "ai21.jamba-1-5-mini-v1:0",
        "contentType": "application/json",
        "accept": "application/json",
        "body": {
        "messages": [
        {
            "role": "user",
            "content": prompt
        }
        ],
        "max_tokens": 1000,
        "top_p": 0.8,
        "temperature": 0.7
        }
        }
    

    payload = {
    "messages": [
        {"role": "user", "content": prompt}
    ],
    "max_tokens": 1000,
    "temperature": 0.7,
    "top_p": 0.9
}


    try:
        bedrock = boto3.client("bedrock-runtime", region_name="us-east-1", config=
                               botocore.config.Config(read_timeout=300, retries={'max_attempts':3}))
        #response=bedrock.invoke_model(body=json.dumps(body))

        # Step 3: Call invoke_model
        response = bedrock.invoke_model(
            modelId="ai21.jamba-1-5-mini-v1:0",
            contentType="application/json",
            accept="application/json",
            body=json.dumps(payload).encode("utf-8")  # Must be bytes
        )
    
        response_content = response.get('body').read()
        response_data = json.load(response_content)
        print(response_data)
        blog_details=response_data['generation']
        return blog_details
    except Exception as e:
        print(f"Error generating the blog:{e}")
        return ""

def save_blog_details_s3(s3_bucket,s3_key,blog_response):
    s3client=boto3.client('s3')

    try:
        s3client.put_object(Bucket = s3_bucket, Key = s3_key, Body =blog_response )
        print("Code saved to s3")
    except Exception as e:
        print("Error when saving the code to s3")


def lambda_handler(event, context):
    #print("Received event: " + json.dumps(event, indent=2))

    print("blog_topic = " + event['blog_topic'])
    blog_topic = event['blog_topic']
    blog_response = blog_generate_uding_bedrock(blogtopic=blog_topic)

    if blog_response:
        current_time=datetime.now().strftime('%H%M%S')
        s3_key=f"blog-output/{current_time}.txt"
        s3_bucket='ashoobucket'
        save_blog_details_s3(s3_bucket,s3_key,blog_response)
        return{
            'statusCode':200,
            'body':json.dumps('Blog Generation is completed')
            }
    else:
        print("No blog was generated")
        return{
            'statusCode':500,
            'body':json.dumps('Blog Generation is incomplete')
            }

    