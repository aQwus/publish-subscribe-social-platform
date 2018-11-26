class Repost{
  int tid;
  int uid;
  int ptid;
  String text;
  int qo;
  MyLinkedList<Repost> reposts;
  MyLinkedList<Reply> replies;
  int time;

  Repost(int time, int uid, int ptid, int tid, String text, int qo){
    this.tid = tid;
    this.uid = uid;
    this.ptid = ptid;
    this.time = time;
    this.text = text;
    this.qo = qo;
    reposts = new MyLinkedList<Repost>();
    replies = new MyLinkedList<Reply>();
  }
}
