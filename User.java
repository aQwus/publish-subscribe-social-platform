class User{
  int uid;
  MyLinkedList<Subscribed> subscribedList;
  MyLinkedList<Integer> subscriberList;
  int lastReadTime = 0;
  MyLinkedList<Post> posts;
  MyLinkedList<Repost> reposts;
  MyLinkedList<Reply> replies;

  User(int uid){
    this.uid = uid;
    lastReadTime = 0;
    subscribedList = new MyLinkedList<Subscribed>();
    subscriberList = new MyLinkedList<Integer>();
    posts = new MyLinkedList<Post>();
    reposts = new MyLinkedList<Repost>();
    replies = new MyLinkedList<Reply>();
  }

  void post(int time, String text, int tid, int qo){
    Post p = new Post(time, this.uid, text, tid, qo);
    posts.Insert(p);
  }

  void repost(int time, int ptid, String text, int tid, int qo){
    Repost rp = new Repost(time, this.uid, ptid, tid, text, qo);
    reposts.Insert(rp);
  }

  void reply(int time, int ptid, String text, int tid, int qo){
    Reply rp = new Reply(time, this.uid, ptid, text, tid, qo);
    replies.Insert(rp);
  }

  Boolean IsSubscribedTo(int uid){
    Node<Subscribed> temp = subscribedList.head;
    while(temp != null){
      if(temp.data.pid == uid) return true;
      temp = temp.next;
    }
    return false;
  }

  MyLinkedList<SearchResult> returnFreshPosts(int lrt, int ts){
    Node<Post> temp = posts.head;
    Node<Repost> temp2 = reposts.head;
    Node<Reply> temp3 = replies.head;
    if(temp != null || temp2 != null || temp3 != null){
      MyLinkedList<SearchResult> texts = new MyLinkedList<>();
      while(temp != null){
        if(temp.data.time >= lrt && temp.data.time >= ts){
          SearchResult sr = new SearchResult(temp.data.text, temp.data.time, temp.data.qo);
          texts.InsertSr(sr);
        }
        temp = temp.next;
      }

      while(temp2 != null){
        if(temp2.data.time >= lrt && temp2.data.time >= ts){
          SearchResult sr = new SearchResult(temp2.data.text, temp2.data.time, temp2.data.qo);
          texts.InsertSr(sr);
        }
        temp2 = temp2.next;
      }

      while(temp3 != null){
        if(temp3.data.time >= lrt && temp3.data.time >= ts){
          SearchResult sr = new SearchResult(temp3.data.text, temp3.data.time, temp3.data.qo);
          texts.InsertSr(sr);
        }
        temp3 = temp3.next;
      }
      return texts;
    } else {
      MyLinkedList<SearchResult> ser = new MyLinkedList<SearchResult>();
      return ser;
    }
  }

}
