terraform {
  backend "s3" {
    bucket         = "terraform-state-priyu"
    key            = "terraform.tfstate"
    region         = "us-east-1"
    encrypt        = true
  }
}
