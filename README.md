# javaLearn
托管代码到GitHub

- 配置 ssh之后，连接github
  git clone git@github.com:yutianaiqingtian/javaLearn.git

- 修改其中的README.md 并且进行add，commit命令，然后通过push就可以将本地仓库的文件传递给远程仓库。

- 如果在运行推送的时候出现如下错误。

  ```
  jhzhang@jhzhang-PC:/usr/codes/java/tmp/javaLearn$ git push origin master 
  对象计数中: 3, 完成.
  Delta compression using up to 4 threads.
  压缩对象中: 100% (2/2), 完成.
  写入对象中: 100% (3/3), 481 bytes | 481.00 KiB/s, 完成.
  Total 3 (delta 0), reused 0 (delta 0)
  remote: error: GH007: Your push would publish a private email address.
  remote: You can make your email public or disable this protection by visiting:
  remote: http://github.com/settings/emails
  To github.com:yutianaiqingtian/javaLearn.git
   ! [remote rejected] master -> master (push declined due to email privacy restrictions)
  error: 无法推送一些引用到 'git@github.com:yutianaiqingtian/javaLearn.git'
  ```

  解决方案可以[参考](https://www.jianshu.com/p/400dbf77f6cf)如下。修改github中 `settting->Emails` 中取消 *Block command line pushes that expose my email*前面的√标记。


# 如何配置Ubuntu和Github

1.  [Connecting to GitHub with SSH](https://help.github.com/articles/connecting-to-github-with-ssh/)
2. [Error: Permission denied (publickey)](https://help.github.com/articles/error-permission-denied-publickey/)

