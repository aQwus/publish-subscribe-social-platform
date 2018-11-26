class Post{
  String text;
  int time;
  int tid;
  int uid;
  int qo;
  MyLinkedList<Repost> reposts;
  MyLinkedList<Reply> replies;

  Post(int time, int uid, String text, int tid, int qo){
    this.text = text;
    this.time = time;
    this.uid = uid;
    this.tid = tid;
    this.qo = qo;
    reposts = new MyLinkedList<Repost>();
    replies = new MyLinkedList<Reply>();
  }
}
