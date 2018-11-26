import java.util.*;

class SearchEngine{
  MyLinkedList<User> users = new MyLinkedList<User>();
  MyLinkedList<Post> posts = new MyLinkedList<Post>();
  MyLinkedList<Repost> reposts = new MyLinkedList<Repost>();
  MyLinkedList<Reply> replies = new MyLinkedList<Reply>();

  void subscribe(int time, int uid, int pid){
    User a = new User(uid);
    User b = new User(pid);
    if(users.GetUser(uid) != null) a = users.GetUser(uid);
    else {
      users.Insert(a);
    }
    if(users.GetUser(pid) != null) b = users.GetUser(pid);
    else {
      users.Insert(b);
    }

    Subscribed p = new Subscribed(pid, time);
    a.subscribedList.Insert(p);
    b.subscriberList.Insert(uid);
    users.updateUser(a);
    users.updateUser(b);
  }

  void unsubscribe(int time, int uid, int pid){
    if(users.GetUser(uid) != null){
      User a = users.GetUser(uid);
      if(users.GetUser(pid) != null){
        User b = users.GetUser(pid);
        if(a.IsSubscribedTo(pid)){
          a.subscribedList.subDelete(pid);
          b.subscriberList.Delete(uid);
          users.updateUser(a);
          users.updateUser(b);
          System.out.println("UNSUBSCRIBE," + time + "," + uid + "," + pid);
        } else System.out.println("uid " + uid + " has not subscribed to pid " + pid);
      }
    }
  }

  void repost(int time, int uid, int ptid, int tid, int qo){
    if(users.GetUser(uid) != null){
      User a = users.GetUser(uid);
      if(posts.GetPost(ptid) != null){
        Post b = posts.GetPost(ptid);
        a.repost(time, b.tid, b.text, tid, qo);
        Repost rp = new Repost(time, uid, b.tid, tid, b.text, qo);
        b.reposts.Insert(rp);
        reposts.Insert(rp);
        users.updateUser(a);
        posts.updatePost(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPOST(" + ptid + ")," + tid);
      } else if(reposts.GetRepost(ptid) != null){
        Repost b = reposts.GetRepost(ptid);
        a.repost(time, b.tid, b.text, tid, qo);
        Repost rp = new Repost(time, uid, b.tid, tid, b.text, qo);
        b.reposts.Insert(rp);
        reposts.Insert(rp);
        users.updateUser(a);
        reposts.updateRepost(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPOST(" + ptid + ")," + tid);
      } else if(replies.GetReply(ptid) != null){
        Reply b = replies.GetReply(ptid);
        a.repost(time, b.tid, b.text, tid, qo);
        Repost rp = new Repost(time, uid, b.tid, tid, b.text, qo);
        reposts.Insert(rp);
        users.updateUser(a);
        replies.updateReply(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPOST(" + ptid + ")," + tid);
      }  else System.out.println("no text with ptid " + ptid);
    } else {
      System.out.println("no user with uid " + uid);
    }
  }

  void reply(int time, int uid, int ptid, String text, int tid, int qo){
    if(users.GetUser(uid) != null){
      User a = users.GetUser(uid);
      if(posts.GetPost(ptid) != null){
        Post b = posts.GetPost(ptid);
        a.reply(time, b.tid, text, tid, qo);
        Reply rp = new Reply(time, uid, b.tid, text, tid, qo);

        User xy = users.GetUser(b.uid);
        Post xyp = xy.posts.GetPost(b.tid);
        xyp.replies.Insert(rp);
        xy.posts.updatePost(xyp);
        users.updateUser(xy);

        b.replies.Insert(rp);
        users.updateUser(a);
        posts.updatePost(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPLY(" + ptid + ")," + text + "," + tid);
      } else if(reposts.GetRepost(ptid) != null){
        Repost b = reposts.GetRepost(ptid);
        a.reply(time, b.tid, text, tid, qo);
        Reply rp = new Reply(time, uid, b.tid, text, tid, qo);

        User xy = users.GetUser(b.uid);
        Repost xyp = xy.reposts.GetRepost(b.tid);
        xyp.replies.Insert(rp);
        xy.reposts.updateRepost(xyp);
        users.updateUser(xy);

        b.replies.Insert(rp);
        replies.Insert(rp);
        users.updateUser(a);
        reposts.updateRepost(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPLY(" + ptid + ")," + text + "," + tid);
      } else if(replies.GetReply(ptid) != null){
        Reply b = replies.GetReply(ptid);
        a.reply(time, b.tid, text, tid, qo);
        Reply rp = new Reply(time, uid, b.tid, text, tid, qo);

        User xy = users.GetUser(b.uid);
        Reply xyp = xy.replies.GetReply(b.tid);
        xyp.replies.Insert(rp);
        xy.replies.updateReply(xyp);
        users.updateUser(xy);

        replies.Insert(rp);
        users.updateUser(a);
        replies.updateReply(b);
        System.out.println("PUBLISH," + time + "," + uid + ",REPLY(" + ptid + ")," + text + "," + tid);
      } else System.out.println("no post with ptid " + ptid);
    } else {
      System.out.println("no user with uid " + uid);
    }
  }

  public void performAction(String actionMessage){
    Scanner s = new Scanner(actionMessage);
    String command = s.next();

    String[] input = command.split(",");

    if(input[0].equals("SUBSCRIBE")){
      int time = Integer.parseInt(input[1]);
      int uid = Integer.parseInt(input[2]);
      int pid = Integer.parseInt(input[3]);
      subscribe(time,uid,pid);
      System.out.println(command);
    }

    else if(input[0].equals("UNSUBSCRIBE")){
      int time = Integer.parseInt(input[1]);
      int uid = Integer.parseInt(input[2]);
      int pid = Integer.parseInt(input[3]);

      if(users.GetUser(uid) == null || users.GetUser(pid) == null){
        if(users.GetUser(uid) == null){
          User a = new User(uid);
          users.Insert(a);
        }
        if(users.GetUser(pid) == null){
          User b = new User(pid);
          users.Insert(b);
        }

        System.out.println("uid " + uid + " has not subscribed to pid " + pid);
      } else {
        unsubscribe(time,uid,pid);
      }
    }

    else if(input[0].equals("PUBLISH")){
      String[] inp = input[3].split("\\(|\\)");
      if(inp[0].equals("NEW")){
        int time = Integer.parseInt(input[1]);
        int uid = Integer.parseInt(input[2]);
        String text = input[4];
        int tid = Integer.parseInt(input[5]);

        if(posts.IsValidPost(tid)&&reposts.IsValidRepost(tid)&&replies.IsValidReply(tid)){
          System.out.println(command);
          User a = new User(uid);
          if(users.GetUser(uid) != null) a = users.GetUser(uid);
          else users.Insert(a);

          a.post(time,text,tid, posts.listSize()+reposts.listSize()+replies.listSize());
          Post p = new Post(time, uid, text, tid,posts.listSize()+reposts.listSize()+replies.listSize());
          posts.Insert(p);
          users.updateUser(a);
        } else System.out.println("Can't publish with tid " + tid);
      }

      else if(inp[0].equals("REPOST")){
        int time = Integer.parseInt(input[1]);
        int uid = Integer.parseInt(input[2]);
        int ptid = Integer.parseInt(inp[1]);
        int tid = Integer.parseInt(input[4]);


        if(posts.IsValidPost(tid)&&reposts.IsValidRepost(tid)&&replies.IsValidReply(tid)){
          repost(time, uid, ptid, tid,posts.listSize()+reposts.listSize()+replies.listSize());
        } else System.out.println("Can't publish with tid " + tid);
      }

      else if(inp[0].equals("REPLY")){
        int time = Integer.parseInt(input[1]);
        int uid = Integer.parseInt(input[2]);
        int ptid = Integer.parseInt(inp[1]);
        String text = input[4];
        int tid = Integer.parseInt(input[5]);

        if(posts.IsValidPost(tid)&&reposts.IsValidRepost(tid)&&replies.IsValidReply(tid)){
          reply(time, uid, ptid, text, tid,posts.listSize()+reposts.listSize()+replies.listSize());
        } else System.out.println("Can't publish with tid " + tid);
      }

    }

    else if(input[0].equals("READ")){
      int time = Integer.parseInt(input[1]);
      int uid = Integer.parseInt(input[2]);

      if(users.GetUser(uid) != null){
        User a = users.GetUser(uid);
        int lrt = a.lastReadTime;
        Node<Subscribed> temp = a.subscribedList.head;
        if(temp == null){
          System.out.println("no text available for uid " + uid);
          return;
        }
        MyLinkedList<SearchResult> texts = new MyLinkedList<SearchResult>();
        while(temp != null){
          User b = users.GetUser(temp.data.pid);
          MyLinkedList<SearchResult> postTexts = b.returnFreshPosts(lrt,temp.data.time);
          if(texts.head != null && postTexts.head != null) texts = texts.Union(postTexts);
          else if(postTexts.head != null) texts = postTexts;
          temp = temp.next;
        }

        Node<Post> posTemp = a.posts.head;
        while(posTemp != null){
          Node<Reply> repTemp = posTemp.data.replies.head;
          while(repTemp != null){
            if(repTemp.data.time >= lrt){
              SearchResult sr = new SearchResult(repTemp.data.text, repTemp.data.time, repTemp.data.qo);
              texts.InsertSr(sr);
            }
            repTemp = repTemp.next;
          }
          posTemp = posTemp.next;
        }

        Node<Repost> reposTemp = a.reposts.head;
        while(reposTemp != null){
          Node<Reply> repTemp = reposTemp.data.replies.head;
          while(repTemp != null){
            if(repTemp.data.time >= lrt){
              SearchResult sr = new SearchResult(repTemp.data.text, repTemp.data.time, repTemp.data.qo);
              texts.InsertSr(sr);
            }
            repTemp = repTemp.next;
          }
          reposTemp = reposTemp.next;
        }

        Node<Reply> replyTemp = a.replies.head;
        while(replyTemp != null){
          Node<Reply> repTemp = replyTemp.data.replies.head;
          while(repTemp != null){
            if(repTemp.data.time >= lrt){
              SearchResult sr = new SearchResult(repTemp.data.text, repTemp.data.time, repTemp.data.qo);
              texts.InsertSr(sr);
            }
            repTemp = repTemp.next;
          }
          replyTemp = replyTemp.next;
        }

        a.lastReadTime = time;
        users.updateUser(a);
        if(texts.listSize() > 0){
          ArrayList<SearchResult> arr = new ArrayList<SearchResult>(texts.listSize());
          Node<SearchResult> n = texts.head;
          while(n != null){
            arr.add(n.data);
            n = n.next;
          }
          MySort m = new MySort();
          ArrayList<SearchResult> sortedArr = new ArrayList<SearchResult>(texts.listSize());
          sortedArr = m.sortThisList(arr);
          ArrayList<String> strArray = new ArrayList<String>(sortedArr.size());
          for(int i=0; i<sortedArr.size();i++){
            strArray.add(sortedArr.get(i).getText());
          }
          System.out.println(command + "," + strArray);
          return;
        } else {
          System.out.println("no text available for uid " + uid);
        }
      } else {
        System.out.println("no user with user id " + uid);
      }
    }

  }

}
