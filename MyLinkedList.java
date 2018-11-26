class MyLinkedList<X>{
  Node<X> head;

  MyLinkedList(){
    head = null;
  }

  public Boolean IsPresent(X ele){
    Node<X> temp = this.head;
    if(temp == null) {
      return false;
    }
    else{
      int c = 0;
      while(temp != null){
        if(ele.equals(temp.data)){
          c = 1;
          break;
        }
        temp = temp.next;
      }
      if(c == 1) return true;
      else return false;
    }
  }

  public void Insert(X ele){
      if(this.head == null){
        this.head = new Node<X>(ele);
      } else if(!(IsPresent(ele))) {
        Node<X> temp = this.head;
        Node<X> newNode = new Node<X>(ele,temp);
        this.head = newNode;
        return;
      }
  }

  public Boolean IsPresentSr(SearchResult ele){
    Node<X> temp = this.head;
    if(temp == null) {
      return false;
    }
    else{
      int c = 0;
      while(temp != null){
        if(ele.equal((SearchResult)temp.data)){
          c = 1;
          break;
        }
        temp = temp.next;
      }
      if(c == 1) return true;
      else return false;
    }
  }

  public void InsertSr(X ele){
      if(this.head == null){
        this.head = new Node<X>(ele);
      } else if(!(IsPresentSr((SearchResult)ele))) {
        Node<X> temp = this.head;
        Node<X> newNode = new Node<X>(ele,temp);
        this.head = newNode;
        return;
      }
  }

  // public void Insert(X ele){
  //     if(this.head == null){
  //       this.head = new Node<X>(ele);
  //     } else if(!(IsPresent(ele))) {
  //       Node<X> temp = this.head;
  //       if(ele.time > temp.data.time){
  //         Node<X> newNode = new Node<X>(ele,temp);
  //         this.head = newNode;
  //         return;
  //       }
  //       while(temp.next != null){
  //         if(ele.time > temp.next.data.time){
  //           Node<X> newNode = new Node<X>(ele);
  //           newNode.next = temp.next;
  //           temp.next = newNode;
  //           return;
  //         }
  //         temp = temp.next;
  //       }
  //       Node<X> newNode = new Node<X>(ele);
  //       temp.next = newNode;
  //     } else {
  //       System.out.println("already subscribed");
  //     }
  // }

  public void Delete(X ele){
    Node<X> temp = this.head;
    if(temp.data.equals(ele)){
      head = temp.next;
      return;
    }
    while(temp.next != null && !(temp.next.data.equals(ele))){
      temp = temp.next;
    }
    if(temp.next != null){
      if(temp.next.data.equals(ele)){
        temp.next = temp.next.next;
      }
    }
  }

  public User GetUser(int uid){
    Node<X> temp = this.head;
    while(temp != null){
      if( ((User)temp.data).uid == uid ) return ((User)temp.data);
      temp = temp.next;
    }
    //System.out.println("no user with user id: " + uid);
    return null;
  }

  // public Post GetPost(int tid){
  //   Node<X> temp = this.head;
  //   while(temp != null){
  //     if( ((Post)temp.data).tid == tid ) return ((Post)temp.data);
  //
  //     Node<Repost> temp3 = ((Post)temp.data).reposts.head;
  //     while(temp3 != null){
  //       if((temp3.data).tid == tid) return ((Post)temp.data);
  //       temp3 = temp3.next;
  //     }
  //
  //     Node<Reply> temp2 = ((Post)temp.data).replies.head;
  //     while(temp2 != null){
  //       if((temp2.data).tid == tid) return ((Post)temp.data);
  //       temp2 = temp2.next;
  //     }
  //
  //     temp = temp.next;
  //   }
  //
  //   temp = this.head;
  //   while(temp != null){
  //     Node<Repost> temp3 = ((Post)temp.data).reposts.head;
  //     while(temp3 != null){
  //       Repost x = temp3.data.reposts.GetRepost(tid);
  //       if(x != null) return ((Post)temp.data);
  //       temp3 = temp3.next;
  //     }
  //     temp = temp.next;
  //   }
  //
  //   //System.out.println("no user with user id: " + uid);
  //   return null;
  // }

  public Post GetPost(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if( ((Post)temp.data).tid == tid ) return ((Post)temp.data);
      temp = temp.next;
    }
    return null;
  }

  public Repost GetRepost(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if( ((Repost)temp.data).tid == tid ) return ((Repost)temp.data);
      temp = temp.next;
    }
    //System.out.println("no user with user id: " + uid);
    return null;
  }

  public Reply GetReply(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if( ((Reply)temp.data).tid == tid ) return ((Reply)temp.data);
      temp = temp.next;
    }
    //System.out.println("no user with user id: " + uid);
    return null;
  }

  public void updateUser(User a){
    int id = a.uid;
    Node<X> temp = this.head;
    if( ((User)temp.data).uid == id ){
      Node<X> newNode = new Node<X>(((X)a),temp.next);
      this.head = newNode;
      return;
    }
    while(temp.next != null){
      if( ((User)temp.next.data).uid == id ){
        Node<X> newNode = new Node<X>(((X)a));
        newNode.next = temp.next.next;
        temp.next = newNode;
        return;
      }
      temp = temp.next;
    }
    System.out.println("no user with user id " + a.uid);
    return;
  }

  public void updatePost(Post a){
    int id = a.tid;
    Node<X> temp = this.head;
    if( ((Post)temp.data).tid == id ){
      Node<X> newNode = new Node<X>(((X)a),temp.next);
      this.head = newNode;
      return;
    }
    while(temp.next != null){
      if( ((Post)temp.next.data).tid == id ){
        Node<X> newNode = new Node<X>(((X)a));
        newNode.next = temp.next.next;
        temp.next = newNode;
        return;
      }
      temp = temp.next;
    }
    System.out.println("no post with id " + a.tid);
    return;
  }

  public void updateRepost(Repost a){
    int id = a.tid;
    Node<X> temp = this.head;
    if( ((Repost)temp.data).tid == id ){
      Node<X> newNode = new Node<X>(((X)a),temp.next);
      this.head = newNode;
      return;
    }
    while(temp.next != null){
      if( ((Repost)temp.next.data).tid == id ){
        Node<X> newNode = new Node<X>(((X)a));
        newNode.next = temp.next.next;
        temp.next = newNode;
        return;
      }
      temp = temp.next;
    }
    System.out.println("no repost with id " + a.tid);
    return;
  }

  public void updateReply(Reply a){
    int id = a.tid;
    Node<X> temp = this.head;
    if( ((Reply)temp.data).tid == id ){
      Node<X> newNode = new Node<X>(((X)a),temp.next);
      this.head = newNode;
      return;
    }
    while(temp.next != null){
      if( ((Reply)temp.next.data).tid == id ){
        Node<X> newNode = new Node<X>(((X)a));
        newNode.next = temp.next.next;
        temp.next = newNode;
        return;
      }
      temp = temp.next;
    }
    System.out.println("no reply with id " + a.tid);
    return;
  }

  public void subDelete(int ele){
    Node<X> temp = this.head;
    if(((Subscribed)temp.data).pid == ele){
      head = temp.next;
      return;
    }
    while(temp.next != null && !(((Subscribed)temp.data).pid == ele)) {
      temp = temp.next;
    }
    if(temp.next != null){
      if(((Subscribed)temp.data).pid == ele) {
        temp.next = temp.next.next;
      }
    }
  }

  public void printUsers(){
    Node<X> temp = this.head;
    if(temp == null){
      System.out.println("empty list");
      return;
    }
    while(temp != null){
      System.out.println(((User)temp.data).uid);
      temp = temp.next;
    }
  }

  public MyLinkedList<X> Union(MyLinkedList<X> l){
    MyLinkedList<X> union = new MyLinkedList<>();
    Node<X> temp1 = this.head;
    if(l.head == null) return this;
    Node<X> temp2 = l.head;

    while(temp1 != null){
      union.Insert(temp1.data);
      temp1 = temp1.next;
    }

    while(temp2 != null){
      if(union.IsPresent(temp2.data)){
        temp2 = temp2.next;
      } else {
        union.Insert(temp2.data);
        temp2 = temp2.next;
      }
    }
    return union;
  }

  public int listSize(){
    int c = 0;
    Node<X> temp = this.head;
    while(temp != null){
      c++;
      temp = temp.next;
    }
    return c;
  }

  public Boolean IsValidPost(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if(((Post)temp.data).tid == tid) return false;
      temp = temp.next;
    }
    return true;
  }

  public Boolean IsValidRepost(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if(((Repost)temp.data).tid == tid) return false;
      temp = temp.next;
    }
    return true;
  }

  public Boolean IsValidReply(int tid){
    Node<X> temp = this.head;
    while(temp != null){
      if(((Reply)temp.data).tid == tid) return false;
      temp = temp.next;
    }
    return true;
  }

}

class Node<X>{
  X data;
  Node<X> next;

  public Node(X d, Node<X> n){
    this.data = d;
    this.next = n;
  }

  public Node(X d){
    this.data = d;
    this.next = null;
  }
}
