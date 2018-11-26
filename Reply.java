class Reply{
  int uid;
  int tid;
  int ptid;
  int time;
  int qo;
  String text;
  MyLinkedList<Reply> replies;

  Reply(int time, int uid, int ptid, String text, int tid, int qo){
    this.uid = uid;
    this.tid = tid;
    this.ptid = ptid;
    this.time = time;
    this.text = text;
    this.qo = qo;
    replies = new MyLinkedList<Reply>();
  }
}
