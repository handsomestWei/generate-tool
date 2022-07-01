## https://developers.google.com/protocol-buffers/docs/javatutorial
## protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto

#################### demo.proto ##############################
# message Demo {
#  required string userName = 1[default=""];
#  required string password = 2[default=""];
# }
##################################################
D:\\libs\\protoc-21.2-win64\\bin\\protoc.exe -I=D:\\ --java_out D:\\demo\\ D:\\demo.proto